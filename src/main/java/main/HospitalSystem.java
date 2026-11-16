
package main;

import database.ConexaoDB;
import hospital.Hospital;
import java.time.LocalDate;
import model.CaixaMedicamento;
import model.Setor;
import relatorio.Dashboard;
import java.util.Scanner;


public class HospitalSystem {

    public static void main(String[] args) {

       ConexaoDB.inicializarBanco();

        Scanner sc = new Scanner(System.in);

        Setor[] setores = {
            new Setor("UTI", "Cardiologia", 10),
            new Setor("Emergencia", "Geral", 10),
            new Setor("Pediatria", "Pediatria", 10)
        };

        Hospital hospital = new Hospital("Hospital Central", setores);

        while(true){
            System.out.println("\n== " + hospital.getNome() + " ==");
            System.out.println("1 - ADICIONAR CAIXA DE MEDICAMENTO");
            System.out.println("2 - RETIRAR CAIXA DO TOPO");
            System.out.println("3 - REMOVER CAIXA ESPECIFICA");
            System.out.println("4 - BUSCAR MEDICAMENTO");
            System.out.println("5 - EXIBIR RELATÓRIO");
            System.out.println("0 - SAIR");

            int opcao = sc.nextInt();
            sc.nextLine();

           switch (opcao) {
                case 1: {
                    System.out.println("\n#===========================================#");
                    System.out.println("#    ADICIONAR CAIXA DE MEDICAMENTO        #");
                    System.out.println("#===========================================#");

                    System.out.print("ID do medicamento: ");
                    String id = sc.nextLine();
                    System.out.print("Nome do medicamento: ");
                    String nomeMedicamento = sc.nextLine();
                    System.out.print("Lote do medicamento: ");
                    String loteMedicamento = sc.nextLine();
                    System.out.print("Data de validade (formato: 2025-12-31): ");
                    String dataStr = sc.nextLine();
                    LocalDate dataValidade = LocalDate.parse(dataStr);
                    System.out.print("Quantidade de medicamentos: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    System.out.println("\n--- Localizacao do Setor ---");
                    System.out.print("Nome do setor (ex: UTI, Emergencia, Pediatria): ");
                    String nomeSetor = sc.nextLine();

                    CaixaMedicamento caixa = new CaixaMedicamento(id, nomeMedicamento, loteMedicamento, dataValidade, quantidade);
                    boolean adicionado = hospital.adicionarCaixaMedicamento(nomeSetor, caixa);

                    if (adicionado) {
                        System.out.println("\n[OK] Caixa adicionada com sucesso!");
                        System.out.println("  " + caixa + " -> Setor: " + nomeSetor);
                        ConexaoDB.registrarMovimentacao("ENTRADA", id, nomeMedicamento, loteMedicamento,
                                                        dataValidade.toString(), quantidade, nomeSetor);
                    } else {
                        System.out.println("\n[ERRO] Operacao falhou! Setor cheio ou ID ja cadastrado.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("\n#===========================#");
                    System.out.println("#      RETIRAR CAIXA DO TOPO        #");
                    System.out.println("#===========================#");

                    System.out.println("\n--- Localizacao do Setor ---");
                    System.out.print("Nome do setor (ex: UTI, Emergencia, Pediatria): ");
                    String nomeSetor = sc.nextLine();

                    CaixaMedicamento retirar = hospital.retirarCaixa(nomeSetor);

                    if (retirar != null) {
                        System.out.println("\n[OK] Caixa retirada com sucesso!");
                        System.out.println("  " + retirar);
                        ConexaoDB.registrarMovimentacao("SAIDA", retirar.getId(), retirar.getNomeMedicamento(),
                                                        retirar.getLote(), retirar.getDataValidade().toString(),
                                                        retirar.getQuantidade(), nomeSetor);
                    } else {
                        System.out.println("\n[ERRO] Setor vazio! Escolha outro setor.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("\n#===========================#");
                    System.out.println("#      REMOVER CAIXA ESPECIFICA        #");
                    System.out.println("#===========================#");

                    System.out.println("\n--- Localizacao do Setor ---");
                    System.out.print("Nome do setor (ex: UTI, Emergencia, Pediatria): ");
                    String nomeSetor = sc.nextLine();
                    System.out.print("ID da caixa: ");
                    String id = sc.nextLine();

                    CaixaMedicamento remover = hospital.removerCaixaMedicamentoEspecifico(nomeSetor, id);

                    if (remover != null) {
                        System.out.println("\n[OK] Caixa removida com sucesso!");
                        System.out.println("  " + remover);
                        ConexaoDB.registrarMovimentacao("REMOCAO_ESPECIFICA", remover.getId(), remover.getNomeMedicamento(),
                                                        remover.getLote(), remover.getDataValidade().toString(),
                                                        remover.getQuantidade(), nomeSetor);
                    } else {
                        System.out.println("\n[ERRO] Caixa nao encontrada! Tente novamente.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("\n#===========================#");
                    System.out.println("#       BUSCAR MEDICAMENTO        #");
                    System.out.println("#===========================#");

                    System.out.print("ID da caixa: ");
                    String id = sc.nextLine();

                    Setor buscar = hospital.buscarCaixa(id);

                    if (buscar != null) {
                        System.out.println("\n[OK] Caixa encontrada!");
                        System.out.println("  " + buscar);
                    } else {
                        System.out.println("\n[ERRO] Caixa nao encontrada! Tente novamente.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("\n#===========================#");
                    System.out.println("#        EXIBIR RELATORIO          #");
                    System.out.println("#===========================#");
                    Dashboard.exibirRelatorio(hospital);
                    break;
                }
                case 0:
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("[AVISO] Opcao invalida! Tente novamente.");
            }
        }


    }
}
