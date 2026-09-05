import java.util.Scanner;

public class Atvividaept1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numerosAvioes = new int[4];
        int[] assentosDisponiveis = new int[4];
        String[] nomesPassageiros = new String[20];
        int[] avioesReservas = new int[20];

        int qtdAvioesCadastrados = 0;
        int totalReservas = 0;
        boolean assentosCadastrados = false;

        int opcao = 0;
        
    
    

    while (opcao != 8) {
            System.out.println("=========================================");
            System.out.println(" SWEET FLIGHT – SISTEMA DE RESERVAS");
            System.out.println("=========================================");
            System.out.println("1 - Cadastrar aviões");
            System.out.println("2 - Cadastrar quantidade de asssentos");
            System.out.println("3 - LIstar aviões");
            System.out.println("4 - Realizar reserva");
            System.out.println("5 - Consultar reservas de um avião");
            System.out.println("6 - Pesquisar passageiro");
            System.out.println("7 - Mostrar resumo");
            System.out.println("8 - Sair");
            System.out.print("\nEscolha uma opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if(opcao==1){
                System.out.println("Digite o número do avião (1 a 4): ");
                int qtd = sc.nextInt();

                while (qtd < 1 || qtd > 4) {
                    System.out.print("Quantidade invalida! Digite um valor entre 1 e 4: ");
                    qtd = sc.nextInt();
                }

                                for (int i = 0; i < qtd; i++) {
                    boolean repetido;
                    int numero;
                    do {
                        repetido = false;
                        System.out.print("Informe o numero do aviao " + (i + 1) + ": ");
                        numero = sc.nextInt();

                        for (int j = 0; j < i; j++) {
                            if (numerosAvioes[j] == numero) {
                                System.out.println("Ja existe um aviao com esse numero Tente outro.");
                                repetido = true;
                                break;
                            }
                        }
                    } while (repetido);

                    numerosAvioes[i] = numero;
                }

                qtdAvioesCadastrados = qtd;
                assentosCadastrados = false;
                System.out.println("Aviões cadastrados com sucesso!");

            } else if (opcao == 2) {
                if (qtdAvioesCadastrados == 0) {
                    System.out.println("Cadastre os aviões primeiro!");
                } else {
                    for (int i = 0; i < qtdAvioesCadastrados; i++) {
                        System.out.print("Quantidade de assentos para o aviao " + numerosAvioes[i] + " (0 a 20): ");
                        int assentos = sc.nextInt();

                        while (assentos < 0 || assentos > 20) {
                            System.out.print("Quantidade invalida! Digite entre 0 e 20: ");
                            assentos = sc.nextInt();
                        }

                        assentosDisponiveis[i] = assentos;
                    }
                    assentosCadastrados = true;
                    System.out.println("Assentos cadastrados com sucesso!");
                }

            } else if (opcao == 3) {
                if (qtdAvioesCadastrados == 0) {
                    System.out.println("Nenhum avião cadastrado.");
                } else {
                    for (int i = 0; i < qtdAvioesCadastrados; i++) {
                        System.out.println("Avião: " + numerosAvioes[i] + " | Assentos disponíveis: " + assentosDisponiveis[i]);
                    }
                }
            }
                else if (opcao == 4) {
                if (qtdAvioesCadastrados == 0 || !assentosCadastrados) {
                    System.out.println("Eh preciso cadastrar aviões e assentos antes!");
                } else if (totalReservas >= 20) {
                    System.out.println("Limite maximo de 20 reservas atingido!");
                } else {
                    System.out.print("Informe o numero do aviao desejado: ");
                    int numAviao = sc.nextInt();
                    sc.nextLine();

                    int indiceAviao = -1;
                    for (int i = 0; i < qtdAvioesCadastrados; i++) {
                        if (numerosAvioes[i] == numAviao) {
                            indiceAviao = i;
                            break;
                        }
                    }

                    if (indiceAviao == -1) {
                        System.out.println("Este avião não existe!");
                    } else if (assentosDisponiveis[indiceAviao] <= 0) {
                        System.out.println("Não há assentos disponíveis para este avião!");
                    } else {
                        String nomePassageiro = "";
                        while (nomePassageiro.trim().isEmpty()) {
                            System.out.print("Informe o nome do passageiro: ");
                            nomePassageiro = sc.nextLine();
                            if (nomePassageiro.trim().isEmpty()) {
                                System.out.println("O nome nao pode ficar vazio!");
                            }
                        }

                        nomesPassageiros[totalReservas] = nomePassageiro;
                        avioesReservas[totalReservas] = numAviao;
                        assentosDisponiveis[indiceAviao]--;
                        totalReservas++;

                        System.out.println("Reserva realizada com sucesso!");
                    }
                }
            } else if (opcao == 5) {
                if (qtdAvioesCadastrados == 0) {
                    System.out.println("Nenhum avião cadastrado!");
                } else {
                    System.out.print("Informe o numero do aviao: ");
                    int numAviao = sc.nextInt();

                    boolean existe = false;
                    for (int i = 0; i < qtdAvioesCadastrados; i++) {
                        if (numerosAvioes[i] == numAviao) {
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        System.out.println("Este avião não existe!");
                    } else {
                        boolean encontrou = false;
                        for (int i = 0; i < totalReservas; i++) {
                            if (avioesReservas[i] == numAviao) {
                                System.out.println("Passageiro: " + nomesPassageiros[i]);
                                encontrou = true;
                            }
                        }

                        if (!encontrou) {
                            System.out.println("Não há reservas realizadas para este avião!");
                        }
                    }
                }
            }else if (opcao == 6) {
                System.out.print("Informe o nome do passageiro: ");
                String nomePesquisa = sc.nextLine();

                boolean encontrou = false;
                for (int i = 0; i < totalReservas; i++) {
                    if (nomesPassageiros[i].equalsIgnoreCase(nomePesquisa)) {
                        System.out.println("Reserva no aviao: " + avioesReservas[i]);
                        encontrou = true;
                    }
                }

                if (!encontrou) {
                    System.out.println("Não há reservas realizadas para este passageiro!");
                }


            } else if (opcao == 7) {
                int totalAssentosDisp = 0;
                int avioesComAssentos = 0;
                int avioesSemAssentos = 0;
                int maiorAssentos = -1;
                int aviaoMaiorAssentos = -1;

                for (int i = 0; i < qtdAvioesCadastrados; i++) {
                    totalAssentosDisp += assentosDisponiveis[i];

                    if (assentosDisponiveis[i] > 0) {
                        avioesComAssentos++;
                    } else {
                        avioesSemAssentos++;
                    }

                    if (assentosDisponiveis[i] > maiorAssentos) {
                        maiorAssentos = assentosDisponiveis[i];
                        aviaoMaiorAssentos = numerosAvioes[i];
                    }
                }

                System.out.println("=== RESUMO ===");
                System.out.println("Quantidade de aviões cadastrados: " + qtdAvioesCadastrados);
                System.out.println("Quantidade total de reservas realizadas: " + totalReservas);
                System.out.println("Quantidade total de assentos disponíveis: " + totalAssentosDisp);
                System.out.println("Quantidade de aviões com assentos disponíveis: " + avioesComAssentos);
                System.out.println("Quantidade de aviões sem assentos disponíveis: " + avioesSemAssentos);
                if (qtdAvioesCadastrados > 0) {
                    System.out.println("Avião com maior quantidade de assentos disponíveis: " + aviaoMaiorAssentos);
                } else {
                    System.out.println("Avião com maior quantidade de assentos disponíveis: Nenhum");
                }

    }else if (opcao == 8) {
                System.out.println("Sistema encerrado. Até logo!");
            } else {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
    


    
}
