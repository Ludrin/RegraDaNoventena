package cmdInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import cache.GuavaCache;

/**
 * @author Ernani
 *
 */
public class CmdInterface {

    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        GuavaCache guavaInstance = GuavaCache.getInstance();
        Set<String> clientCompanies = new HashSet<>();
        byte option = -1;

        try {
            do {
                do {
                    System.out.println("\n ***************  REGRA DA NOVENTENA  ***************** ");
                    System.out.println(" [1] INCLUIR EMPRESA CLIENTE ");
                    System.out.println(" [2] LER TODAS EMPRESAS CADASTRADAS ");
                    System.out.println(" [3] VERIFICAR SE EMPRESA DEVE SER AFETADA POR PESQUISA ");
                    System.out.println(" [4] REGISTRAR EMPRESA NA QUAL PESQUISA FOI REALIZADA");
                    System.out.println(" [5] VERIFICAR TODAS AS EMPRESAS QUE NÃO DEVEM SER AFETADAS POR PESQUISA ");
                    System.out.println(" [6] REMOVER EMPRESA ESPECÍFICA DA REGRA DA NOVENTENA ");
                    System.out.println(" [7] REMOVER TODAS AS EMPRESAS DA REGRA DA NOVENTENA (LIMPAR CACHE)");
                    System.out.println(" [0] SAIR");
                    System.out.print("\nDigite a opção desejada: ");
                    option = Byte.parseByte(read.readLine());
                    if (option < 0 || option > 7) {
                        System.out.println("Opcao Inválida. Digite novamente.\n");
                    }
                } while (option < 0 || option > 7);

                switch (option) {
                case 0:
                    System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
                    break;
                case 1:
                    addClientCompany(read, clientCompanies);
                    break;
                case 2:
                    readAllClientCompanies(clientCompanies);
                    break;
                case 3:
                    checkIfCompanyShouldBeAffectedBySurvey(guavaInstance, read, clientCompanies);
                    break;
                case 4:
                    registerSurvey(guavaInstance, read, clientCompanies);
                    break;
                case 5:
                    listAllCompaniesInCache(guavaInstance);
                    break;
                case 6:
                    removeEntry(guavaInstance, read, clientCompanies);
                    break;
                case 7:
                    removeAllEntries(guavaInstance);
                    break;
                }
            } while (option != 0);
            read.close();

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    private static void addClientCompany(BufferedReader read, Set<String> clientCompanies) throws IOException {
        System.out.print("Digite o nome da nova empresa cliente: ");
        String clientCompany = read.readLine().toUpperCase();
        clientCompanies.add(clientCompany);
    }

    private static void readAllClientCompanies(Set<String> clientCompanies) {
        System.out.println("Empresas clientes no banco de dados: " + clientCompanies.toString());
    }

    private static void checkIfCompanyShouldBeAffectedBySurvey(GuavaCache guavaInstance, BufferedReader read, Set<String> clientCompanies) throws IOException {
        System.out.print("Digite o nome da empresa cliente: ");
        String clientCompany = read.readLine().toUpperCase();

        if (clientCompanies.contains(clientCompany)) {
            if (guavaInstance.isPresent(clientCompany)) {
                System.out.println("Cliente " + clientCompany + " já foi afetado por pesquisa nos últimos 90 dias e não deve ser afetado novamente.");
            } else {
                System.out.println("Cliente " + clientCompany + " não foi afetado por pesquisa nos últimos 90 dias. Pesquisa pode ser feita.");
            }
        } else {
            System.out.println("Cliente não existe na base de dados.");
        }
    }

    private static void registerSurvey(GuavaCache guavaInstance, BufferedReader read, Set<String> clientCompanies) throws IOException {
        System.out.print("Digite o nome da empresa cliente: ");
        String clientCompany = read.readLine().toUpperCase();

        if (clientCompanies.contains(clientCompany)) {
            if (guavaInstance.isPresent(clientCompany)) {
                System.out.println("Cliente " + clientCompany + " já foi afetado por pesquisa nos últimos 90 dias e não deve ser afetado novamente.");
            } else {
                try {
                    guavaInstance.getEntry(clientCompany);
                } catch (ExecutionException e) {
                    System.out.println("Erro ao registrar em cache: ");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Cliente não existe na base de dados.");
        }
    }

    private static void listAllCompaniesInCache(GuavaCache guavaInstance) {
        try {
            System.out.println("Todas as empresas com pesquisas realizadas nos últimos 90 dias (em cache): " + guavaInstance.getAllEntries());
        } catch (ExecutionException e) {
            System.out.println("Erro ao ler em cache: ");
            e.printStackTrace();
        }
    }

    private static void removeEntry(GuavaCache guavaInstance, BufferedReader read, Set<String> clientCompanies) throws IOException {
        System.out.print("Digite o nome da empresa cliente: ");
        String clientCompany = read.readLine().toUpperCase();

        if (clientCompanies.contains(clientCompany)) {
            if (guavaInstance.isPresent(clientCompany)) {
                guavaInstance.removeEntry(clientCompany);
            } else {
                System.out.println("Cliente " + clientCompany + " não foi afetado por pesquisa nos últimos 90 dias.");
            }
        } else {
            System.out.println("Cliente não existe na base de dados.");
        }
    }

    private static void removeAllEntries(GuavaCache guavaInstance) {
        guavaInstance.removeAllEntries();
    }

}
