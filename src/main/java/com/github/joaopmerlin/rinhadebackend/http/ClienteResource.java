package com.github.joaopmerlin.rinhadebackend.http;

import com.github.joaopmerlin.rinhadebackend.entity.TipoTransacao;
import com.github.joaopmerlin.rinhadebackend.entity.Transacao;
import com.github.joaopmerlin.rinhadebackend.http.dto.*;
import com.github.joaopmerlin.rinhadebackend.repository.ClienteRepository;
import com.github.joaopmerlin.rinhadebackend.repository.TransacaoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public ClienteResource(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    @PostMapping("/{clienteId}/transacoes")
    public RegistraTransacaoResponse registraTransacao(@PathVariable Integer clienteId,
                                                       @RequestBody RegistraTransacaoRequest request) {
        var cliente = clienteRepository.findByIdForUpdate(clienteId).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

        if (!request.valida()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422));
        }

        var tipoTransacao = TipoTransacao.valueOf(request.tipo());
        var valor = Integer.parseInt(request.valor());

        if (!cliente.temLimite(valor, tipoTransacao)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422));
        }

        var transacao = new Transacao(null, clienteId, valor, tipoTransacao, request.descricao(), OffsetDateTime.now());
        transacaoRepository.save(transacao);

        cliente.atualizaSaldo(valor, tipoTransacao);
        clienteRepository.save(cliente);

        return new RegistraTransacaoResponse(cliente.limite(), cliente.saldo());
    }

    @Transactional(readOnly = true)
    @GetMapping("/{clienteId}/extrato")
    public ExtratoResponse extrato(@PathVariable Integer clienteId) {
        var cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

        var trasacoes = transacaoRepository.findByClienteId(clienteId).stream()
                .map(e -> new ExtratoTransacoesResponse(e.valor(), e.tipo(), e.descricao(), e.realizadaEm())).toList();

        var saldo = new ExtratoSaldoResponse(cliente.saldo(), OffsetDateTime.now(), cliente.limite());

        return new ExtratoResponse(saldo, trasacoes);
    }
}
