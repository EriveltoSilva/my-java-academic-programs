
//import java.util.Date;
import java.util.Scanner;

//import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.InputMismatchException;

public class App {

    private static Scanner teclado = new Scanner(System.in);
    private static Fila<RegistroPaciente> filaParaRastreio = new Fila<RegistroPaciente>();
    private static FilaPrioridade<RastreioPaciente> filaParaPagamento = new FilaPrioridade<RastreioPaciente>();
    private static FilaPrioridade<Pagamento> filaParaAtendimento = new FilaPrioridade<Pagamento>();
    private static Lista<Atendimento> listaAtendidos = new Lista<Atendimento>();
    private static ListaLinear<RegistroMedico> listaMedicos;

    static {
        listaMedicos = lerFileRegistroMedicos();
    }

    public static void limparBuffer() {
        teclado.nextLine();
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                new ProcessBuilder("cmd", "/c", "color 0A").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void fazerRegistro() {
        String opcao;
        do {
            limparTela();
            RegistroPaciente registro = new RegistroPaciente();
            System.out.println("--------------AREA DE REGISTRO DE PACIENTE-------------\n");
            System.out.print("Digite o nome do paciente!\nR:");
            registro.setNome(teclado.nextLine());

            System.out.print("Digite o BI do paciente!\nR:");
            registro.setBI(teclado.nextLine());

            System.out.print("Digite a idade do paciente!\nR:");
            registro.setIdade(teclado.nextInt());
            limparBuffer();

            System.out.print("Digite a morada do paciente!\nR:");
            registro.setMorada(teclado.nextLine());

            System.out.println("O dados guardados do paciente sao:");
            System.out.println(registro.toString());
            System.out.print("Deseja guardar esse registro do paciente(S/N)\nR:");
            if (teclado.nextLine().equalsIgnoreCase("S")) {
                registro.setData();
                filaParaRastreio.enfileirar(registro);
                System.out.println("Dados guardados!");
            } else {
                System.out.println("Registro Cancelado!");
            }

            System.out.println("Deseja fazer o proximo Registro?(S/N)");
            System.out.print("R:");
            opcao = teclado.nextLine();

        } while (opcao.equalsIgnoreCase("S"));

    }

    public static void fazerRastreio() {
        String opcao;
        do {
            limparTela();

            System.out.println("--------------AREA DE RASTREIO DE PACIENTE-------------\n");
            if (filaParaRastreio.totalElementos() == 0) {
                System.out.println("Nao ha Pacientes na Fila para Rastreio!");
                System.out.println("Deseja permanecer aqui mesmo assim?(S/N)");
                System.out.print("R:");
            } else {
                System.out.println(filaParaRastreio.toString());
                System.out.println("Deseja fazer o proximo Rastreio(S/N)?");
                System.out.print("R:");
                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    RegistroPaciente paciente = filaParaRastreio.primeiroNaFila();
                    RastreioPaciente rastreio = new RastreioPaciente(paciente);
                    System.out.println("Paciente:" + paciente.getNome());

                    System.out.print("Digite o valor da Temperatura medida:!\nR:");
                    rastreio.setTemperatura(teclado.nextFloat());
                    limparBuffer();

                    System.out.print("Digite o valor do Peso medido:!\nR:");
                    rastreio.setPeso(teclado.nextFloat());
                    limparBuffer();

                    System.out.print("Digite o valor da Pressao Arterial medido:!\nR:");
                    rastreio.setPressaoArterial(teclado.nextDouble());
                    limparBuffer();

                    System.out.println("Os dados rastreiados no paciente sao:");
                    System.out.println(rastreio.toString());
                    System.out.print("Deseja guardar esse rastreio do paciente(S/N)\nR:");
                    if (teclado.nextLine().equalsIgnoreCase("S")) {
                        try {
                            filaParaRastreio.desenfileirar();
                        } catch (NullPointerException e) {
                            System.out.print("ERRO: A FILA JA ESTA VAZIA, IMPOSSIVEL DESENFILEIRAR!");
                        }
                        filaParaPagamento.enfileirar(rastreio, rastreio.getEstado());
                        System.out.println("Dados guardados!");
                    } else {
                        System.out.println("Registro de Rastreio Cancelado!");
                        System.out
                                .println("Eliminar " + rastreio.getPaciente().getNome() + " da fila de rastreio(S/N)?");
                        System.out.print("R:");
                        String escolha = teclado.nextLine();

                        if (escolha.equalsIgnoreCase("S"))
                            filaParaRastreio.desenfileirar();
                    }

                }
                System.out.println("Deseja fazer o proximo Rastreio(S/N)?");
                System.out.print("R:");
            }

            opcao = teclado.nextLine();
        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void fazerPagamento() {
        String opcao;
        do {
            limparTela();

            System.out.println("--------------AREA DE PAGAMENTO DO PACIENTE-------------\n");
            if (filaParaPagamento.totalElementos() == 0) {
                System.out.println("Nao ha Pacientes na Fila para Pagamento!");
                System.out.println("Deseja permanecer aqui mesmo assim(S/N)?");
                System.out.print("R:");
            } else {
                System.out.println(filaParaPagamento.toString());
                System.out.println("Deseja fazer o proximo Pagamento(S/N)?");
                System.out.print("R:");
                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    RastreioPaciente rastreio = filaParaPagamento.primeiroNaFila();
                    Pagamento pagamento = new Pagamento(rastreio);
                    System.out.println("Paciente.........................:" + rastreio.getPaciente().getNome());
                    System.out.println(rastreio.toString());
                    System.out.print("Digite o valor do pagamento a fazer:!\nR:");
                    pagamento.setValorPagamento(teclado.nextDouble());
                    limparBuffer();

                    System.out.println("Os dados do pagamento do paciente registrados sao:");
                    System.out.println(pagamento.toString());
                    System.out.print("Deseja guardar esse pagamento do paciente(S/N)\nR:");
                    if (teclado.nextLine().equalsIgnoreCase("S")) {
                        if (filaParaPagamento.desenfileirar() != null) {
                            filaParaAtendimento.enfileirar(pagamento, pagamento.getRastreio().getEstado());
                            System.out.println("Dados guardados!");
                        } else
                            System.out.println("ERRO: FILA PARA PAGAMENTO VAZIA");

                    } else {
                        System.out.println("Registro de Pagamento Cancelado!");
                        System.out.println("Eliminar " + rastreio.getPaciente().getNome() + " da fila(S/N)?");
                        System.out.print("R:");
                        String escolha = teclado.nextLine();

                        if (escolha.equalsIgnoreCase("S"))
                            filaParaPagamento.desenfileirar();
                    }
                }
                System.out.println("Deseja fazer o proximo Pagamento(S/N)?");
                System.out.print("R:");
            }

            opcao = teclado.nextLine();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void fazerAtendimento() {
        String opcao;
        do {
            limparTela();

            System.out.println("--------------AREA DO ATENDIMENTO DO PACIENTE-------------\n");
            if (filaParaAtendimento.totalElementos() == 0) {
                System.out.println("Nao ha Pacientes na Fila para Atendimento!");
                System.out.println("Deseja permanecer aqui mesmo assim(S/N)?");
                System.out.print("R:");
            } else {
                System.out.println(filaParaAtendimento.toString());
                System.out.println("Deseja fazer o proximo Atendimento(S/N)?");
                System.out.print("R:");
                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    Pagamento pagamento = filaParaAtendimento.primeiroNaFila();
                    Atendimento atendimento = new Atendimento(pagamento);
                    System.out.println(
                            "Paciente.........................:" + pagamento.getRastreio().getPaciente().getNome());

                    System.out.print("Digite o Diagnostico feito!\nR:");
                    atendimento.setDoencaDiagnosticada(teclado.nextLine());
                    int posicao = (int) (Math.random() * 100) % listaMedicos.totalElementos();
                    atendimento.setMedico(listaMedicos.pegarElemento(posicao));

                    System.out.println("Os dados do atendimento do paciente registrados sao:");
                    System.out.println(atendimento.toString());
                    System.out.print("Deseja guardar os dados desse atendimento do paciente(S/N)\nR:");
                    if (teclado.nextLine().equalsIgnoreCase("S")) {
                        atendimento.setDataAtendimento();
                        filaParaAtendimento.desenfileirar();
                        listaAtendidos.adicionarFim(atendimento);
                        System.out.println("Dados guardados!");
                    } else {
                        System.out.println("Registro de Atendimento Cancelado!");
                        System.out.println(
                                "Eliminar " + pagamento.getRastreio().getPaciente().getNome() + " da fila(S/N)?");
                        System.out.print("R:");
                        String escolha = teclado.nextLine();

                        if (escolha.equalsIgnoreCase("S"))
                            filaParaAtendimento.desenfileirar();
                    }
                }
                System.out.println("Deseja fazer o proximo Atendimento(S/N)?");
                System.out.print("R:");
            }

            opcao = teclado.nextLine();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void listarAtendidos() {
        String opcao;
        do {
            limparTela();

            System.out.println("--------------LISTA DOS PACIENTES ATENDIDOS-------------\n");
            if (listaAtendidos.totalElementos() == 0)
                System.out.println("Nao tem nenhum paciente na lista de atendimento de hoje!");
            else
                System.out.println(listaAtendidos.toString());
            System.out.println("Deseja ver a atualizacao da lista(S/N)?");
            System.out.print("R:");
            opcao = teclado.nextLine();

        } while (opcao.equalsIgnoreCase("S"));
    }

    private static void adicionarNovoMedico() {
        RegistroMedico medico = new RegistroMedico();
        limparTela();
        System.out.println("------------CADASTRO DE NOVO MEDICO---------");
        System.out.print("Digite o Nome do Novo Medico:");
        medico.setNome(teclado.nextLine());
        System.out.print("Digite a Especialidade do Novo Medico:");
        medico.setEspecializacao(teclado.nextLine());
        medico.setId(listaMedicos.totalElementos() + 1);
        listaMedicos.adicionarFim(medico);
        gravarFileRegistroMedico(listaMedicos);
    }

    private static boolean alterarRegistroMedico() {
        limparTela();
        System.out.println("------------ALTERACAO DO CADASTRO MEDICO---------");
        System.out.print("Digite o Nome do Medico a Alterar o Cadastro:");
        String nomeMedico = teclado.nextLine();
        int i;
        for (i = 0; i < listaMedicos.totalElementos(); i++) {
            RegistroMedico medico = listaMedicos.pegarElemento(i);
            if (medico.getNome().equalsIgnoreCase(nomeMedico)) {
                System.out.println("Nome encontrado!");
                System.out.print("Digite o Nome alterado do Medico:");
                medico.setNome(teclado.nextLine());

                System.out.print("Digite a Especializacao alterada do Medico:");
                medico.setEspecializacao(teclado.nextLine());

                listaMedicos.alterarPosicao(medico, i);
                gravarFileRegistroMedico(listaMedicos);
                return true;
            }
        }

        return false;
    }

    private static boolean eliminarMedico() {
        limparTela();
        System.out.println("------------ELIMINACAO DE CADASTRO MEDICO---------");
        System.out.print("Digite o Nome do Medico a Eliminar o Cadastro:");
        String nomeMedico = teclado.nextLine();
        int i;
        for (i = 0; i < listaMedicos.totalElementos(); i++)
            if (listaMedicos.pegarElemento(i).getNome().equalsIgnoreCase(nomeMedico)) {
                System.out.println("Nome encontrado! Iniciando Eliminacao...");
                listaMedicos.removerPosicao(i);
                gravarFileRegistroMedico(listaMedicos);
                return true;
            }
        return false;

    }

    public static void listarMedicos() {
        String opcao;
        do {
            limparTela();
            listaMedicos = lerFileRegistroMedicos();

            System.out.println("--------------LISTA DOS MEDICOS EM SERVICO-------------\n");
            System.out.println(listaMedicos.toString());
            System.out.println("\n\n");
            System.out.println("Qual operacao deseja realizar sobre a lista de Medicos?");
            System.out.println("1) Adicionar Novo Medico");
            System.out.println("2) Actualizar Informacao");
            System.out.println("3) Eliminar Medico");
            System.out.print("4) Nenhum das acima\nR:");
            int opcaoSelecionada = teclado.nextInt();
            limparBuffer();

            switch (opcaoSelecionada) {
                case 1:
                    adicionarNovoMedico();
                    break;
                case 2:
                    if (alterarRegistroMedico())
                        System.out.println("Alteracao Realizada com Sucesso!");
                    else
                        System.out.println(
                                "Erro: Falha na Alsteracao do Registro Medico, possivelmente o nome esta incorreto!");
                    break;
                case 3:
                    if (eliminarMedico()) {
                        System.out.println("Eliminacao Terminada com Sucesso!");
                    } else
                        System.out.println(
                                "Erro: Falha na Eliminacao do Registro Medico, possivelmente o nome esta incorreto!");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcao de Selecao Invalida!");
            }

            System.out.println("Deseja ver a atualizacao da lista de medicos(S/N)?");
            System.out.print("R:");
            opcao = teclado.nextLine();

        } while (opcao.equalsIgnoreCase("S"));
    }

    public static void fazerFecho() {
        String opcao;
        do {
            limparTela();

            System.out.println("--------------FECHO DO DIA-------------\n");
            if (filaParaAtendimento.isVazia()) {
                StringBuilder fichaImpressao = new StringBuilder(
                        "---------Ficha de Fecho do Dia " + new Date().toString() + "--------\n\n");
                fichaImpressao.append(listaAtendidos.toString());
                fichaImpressao.append("\n\n\t\tProcessado pelo software de..:Victória Idalina Garcia Mumpasi (Vitche)");
                System.out.println(fichaImpressao.toString());
                gravarFicheiro(Paths.get("arquivos/Impressao_de_Fecho.txt"), fichaImpressao.toString());
            } else {
                System.out.println("ERRO:Ainda ha pacientes na Fila de Atendimento!");
            }

            System.out.println("Fecho realizado! Fechar outra secao(S/N)?");
            System.out.print("R:");
            opcao = teclado.nextLine();
        } while (opcao.equalsIgnoreCase("S"));
    }

    private static void gravarFileRegistroMedico(ListaLinear<RegistroMedico> registro) {
        Path caminho = Paths.get("arquivos/ListaMedicos.txt");
        Charset utf8 = StandardCharsets.UTF_8;

        try (BufferedWriter w = Files.newBufferedWriter(caminho, utf8)) {
            for (int i = 0; i < registro.totalElementos(); i++) {
                w.write(registro.pegarElemento(i).getNome() + ";");
                w.write(registro.pegarElemento(i).getEspecializacao() + ";");
                w.write(registro.pegarElemento(i).getId() + ";\n");
            }

        } catch (IOException e) {
            System.out.println("Erro Escrevendo no ficheiro!");
            e.printStackTrace();
        }
        System.out.println("Registrado com sucesso!");
    }

    private static ListaLinear<RegistroMedico> lerFileRegistroMedicos() {
        ListaLinear<RegistroMedico> registro = new ListaLinear<RegistroMedico>();
        Path caminho = Paths.get("arquivos/ListaMedicos.txt");
        Charset utf8 = StandardCharsets.UTF_8;

        try (BufferedReader r = Files.newBufferedReader(caminho, utf8)) {
            String linha = null;
            while ((linha = r.readLine()) != null) {
                String[] txt = linha.split(";");
                registro.adicionarFim(new RegistroMedico(txt[0], txt[1], Integer.parseInt(txt[2])));
            }
        } catch (IOException e) {
            System.out.println("ERRO ao Ler File da Lista de Medicos");
        }

        return registro;

    }

    private static boolean gravarFicheiro(Path caminho, String texto) {
        Charset utf8 = StandardCharsets.UTF_8;
        try (BufferedWriter w = Files.newBufferedWriter(caminho, utf8)) {
            w.write(texto);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao Gravar no Ficheiro!");
            e.printStackTrace();
            return false;
        }

    }

    private static void imprimirMenuPrincipal() {
        System.out.println("################## WELCOME TO SISTEMA DE GERENCIAMENTO DE CLINICA ###############");
        System.out.println("#                                                                               #");
        System.out.println("#-------------------------------------------------------------------------------#");
        System.out.println("#                                                                               #");
        System.out.println("#                                                                               #");
        System.out.println("#                              MENU PRINCIPAL                                   #");
        System.out.println("#                                                                               #");
        System.out.println("# 1.Dar Entrada de Paciente                                                     #");
        System.out.println("# 2.Fazer Rastreio de Paciente                                                  #");
        System.out.println("# 3.Fazer Pagamento de Atendimento                                              #");
        System.out.println("# 4.Fazer Atendimento de Paciente                                               #");
        System.out.println("# 5.Listar Pacientes Atendidos                                                  #");
        System.out.println("# 6.Listar Medicos em Servico                                                   #");
        System.out.println("# 7.Fecho do dia                                                                #");
        System.out.println("# 8.Sair do Programa                                                            #");
        System.out.println("#                                                                               #");
        System.out.println("#-------------------------------------------------------------------------------#");
        System.out.println("Digite por favor o numero da opcao que deseja.");
        System.out.print("R:");
        // System.out.println(listaMedicos.toString());

    }

    public static void mensagemEnter() {
        System.out.println("Digite <Enter> para continuar!");
        teclado.nextLine();
    }

    public static void main(String[] args) throws Exception {

        int opcao;
        do {

            limparTela();

            imprimirMenuPrincipal();

            try {
                opcao = teclado.nextInt();
                
            } catch (InputMismatchException e) {
                opcao=9;
            }finally{
                limparBuffer();
            }

            switch (opcao) {

                case 1:// Registro de Pacientes
                    fazerRegistro();
                    break;
                case 2:// Rastreio de Pacientes
                    fazerRastreio();
                    break;
                case 3:// Pagamento de Atendimento
                    fazerPagamento();
                    break;
                case 4:// Atendimento do Paciente
                    fazerAtendimento();
                    break;
                case 5:// Listar Pacientes Atendidos
                    listarAtendidos();
                    break;
                case 6:// Listar Medicos em Serviços
                    listarMedicos();
                    break;
                case 7:// Fecho do Dia
                    fazerFecho();
                    break;
                case 8:// Sair do Programa
                    System.out.println("Voce escolheu a opcao Sair, obrigado por usar o Programa!");
                    break;
                default:
                    System.out.println("Opcao Invalida!");
                    mensagemEnter();

            }
        } while (opcao != 8);

        teclado.close();

    }

}// Fim da class App