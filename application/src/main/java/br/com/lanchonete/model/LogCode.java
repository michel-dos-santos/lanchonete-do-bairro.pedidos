package br.com.lanchonete.model;

import java.util.Arrays;

public abstract class LogCode {
    public static LogCodeInfo INFO;
    public static LogCodeDebug DEBUG;
    public static LogCodeWarn WARN;
    public static LogCodeError ERROR;

    public enum LogCodeInfo {
        _0001("Iniciando o processo de criação do cliente no sistema"),
        _0002("Validando a consistência dos dados do pedido"),
        _0003("Iniciando o processo de checkout do pedido no sistema"),
        _0004("Pedido criado com sucesso"),
        _0005("Iniciando o processo de fazer a cobrança do pedido no sistema"),
        _0006("Cobrança do pedido criado com sucesso"),
        _0007("Iniciando o processo de busca dos pedidos com base no status no sistema"),
        _0008("Pedidos encontrados com sucesso"),
        _0009("Iniciando o processo de notificação da cobrança do pedido para o hub de pagamento"),
        _0010("Notificação da cobrança do pedido para o hub de pagamento realizada com sucesso"),
        _0011("Enviando a cobrança para o sistema do Itaú"),
        _0012("Envio da cobrança para o sistema do Itaú realizada com sucesso"),
        _0013("Recebido informação da cobrança com sucesso"),
        _0014("Atualizado informação da cobrança com sucesso"),
        _0015("Buscando dados do meu pedido com base no id"),
        _0016("Meu pedido encontrado com sucesso"),
        _0017("Atualizando status do pedido com base no id"),
        _0018("Status do pedido atualizado com sucesso"),
        _0019("Buscando pedidos para listagem do monitor"),
        _0020("Pedidos para listagem do monitor encontrado com sucesso"),
        ;

        private String description;

        LogCodeInfo(String description) {
            this.description = description;
        }

        public static LogCodeInfo get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeDebug {
        _0001("Template");

        private String description;

        LogCodeDebug(String description) {
            this.description = description;
        }

        public static LogCodeDebug get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeWarn {
        _0001("Template");

        private String description;

        LogCodeWarn(String description) {
            this.description = description;
        }

        public static LogCodeWarn get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }

    public enum LogCodeError {
        _0001("Template");

        private String description;

        LogCodeError(String description) {
            this.description = description;
        }

        public static LogCodeError get(String type) {
            return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
        }

        public String getDescription() {
            return this.description;
        }

    }
}
