import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Ciclo> ciclos = new ArrayList<>();
    static ArrayList<Sintomas> sintomas = new ArrayList<>();
    static ArrayList<CicloMenstrual> ciclosMenstruais = new ArrayList<>();

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Usuário");
            System.out.println("2 - Ciclo Mensal");
            System.out.println("3 - Sintomas");
            System.out.println("4 - Ciclo Menstrual");
            System.out.println("0 - Sair");

            op = sc.nextInt();

            switch (op) {
                case 1: menuUsuario(); break;
                case 2: menuCiclo(); break;
                case 3: menuSintomas(); break;
                case 4: menuCicloMenstrual(); break;
            }

        } while (op != 0);
    }

    static void menuUsuario() {

        int op;

        do {
            System.out.println("\n--- USUÁRIO ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:

                    Usuario u = new Usuario();

                    System.out.print("ID: ");
                    u.setIdUsuario(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Nome: ");
                    u.setNome(sc.nextLine());

                    System.out.print("Nascimento: ");
                    u.setDataNascimento(LocalDate.parse(sc.nextLine()));

                    usuarios.add(u);
                    break;

                case 2:

                    usuarios.forEach(user ->
                            System.out.println(
                                    "ID: " + user.getIdUsuario() +
                                            " | Nome: " + user.getNome() +
                                            " | Nascimento: " + user.getDataNascimento()
                            ));
                    break;

                case 3:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    for (Usuario user : usuarios) {

                        if (user.getIdUsuario() == id) {

                            System.out.print("Novo nome: ");
                            user.setNome(sc.nextLine());
                        }
                    }

                    break;

                case 4:

                    System.out.print("ID: ");
                    int del = sc.nextInt();

                    usuarios.removeIf(u1 -> u1.getIdUsuario() == del);
                    System.out.print("Excluído! ");
                    break;
            }

        } while (op != 0);
    }

    static void menuCiclo() {

        int op;

        do {
            System.out.println("\n--- CICLO MENSAL ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:

                    Ciclo c = new Ciclo();

                    System.out.print("ID: ");
                    c.setIdCiclo(sc.nextInt());

                    System.out.print("ID usuário: ");
                    c.setIdUsuario(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Início: ");
                    c.setDataInicio(LocalDate.parse(sc.nextLine()));

                    System.out.print("Fim: ");
                    c.setDataFinal(LocalDate.parse(sc.nextLine()));

                    ciclos.add(c);
                    break;

                case 2:

                    ciclos.forEach(c1 ->
                            System.out.println(
                                    "ID: " + c1.getIdCiclo() +
                                            " | Usuário: " + c1.getIdUsuario() +
                                            " | Início: " + c1.getDataInicio() +
                                            " | Fim: " + c1.getDataFinal()
                            ));
                    break;

                case 3:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    for (Ciclo c1 : ciclos) {

                        if (c1.getIdCiclo() == id) {

                            System.out.print("Nova data início: ");
                            c1.setDataInicio(LocalDate.parse(sc.nextLine()));

                            System.out.print("Nova data final: ");
                            c1.setDataFinal(LocalDate.parse(sc.nextLine()));
                        }
                    }

                    break;

                case 4:

                    System.out.print("ID: ");
                    int del = sc.nextInt();

                    ciclos.removeIf(c1 -> c1.getIdCiclo() == del);
                    System.out.print("Excluído! ");
                    break;
            }

        } while (op != 0);
    }

    static void menuSintomas() {

        int op;

        do {
            System.out.println("\n--- SINTOMAS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:

                    Sintomas s = new Sintomas();

                    System.out.print("ID: ");
                    s.setIdSintomas(sc.nextInt());

                    System.out.print("ID ciclo: ");
                    s.setIdCiclo(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Descrição: ");
                    s.setDescricao(sc.nextLine());

                    System.out.print("Intensidade: ");
                    s.setIntensidade(sc.nextInt());

                    sintomas.add(s);
                    break;

                case 2:

                    sintomas.forEach(s1 ->
                            System.out.println(
                                    "ID: " + s1.getIdSintomas() +
                                            " | Descrição: " + s1.getDescricao() +
                                            " | Intensidade: " + s1.getIntensidade()
                            ));
                    break;

                case 3:

                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    for (Sintomas s1 : sintomas) {

                        if (s1.getIdSintomas() == id) {

                            System.out.print("Nova descrição: ");
                            s1.setDescricao(sc.nextLine());
                        }
                    }

                    break;

                case 4:

                    System.out.print("ID: ");
                    int del = sc.nextInt();

                    sintomas.removeIf(s1 -> s1.getIdSintomas() == del);
                    System.out.print("Excluído! ");
                    break;
            }

        } while (op != 0);
    }

    static void menuCicloMenstrual() {

        int op;

        do {
            System.out.println("\n--- CICLO MENSTRUAL ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:

                    CicloMenstrual cm = new CicloMenstrual();

                    System.out.print("ID: ");
                    cm.setIdCicloMenstrual(sc.nextInt());

                    System.out.print("ID ciclo: ");
                    cm.setIdCiclo(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Início: ");
                    cm.setDataInicio(LocalDate.parse(sc.nextLine()));

                    System.out.print("Fim: ");
                    cm.setDataFinal(LocalDate.parse(sc.nextLine()));

                    System.out.print("Fluxo: ");
                    cm.setIntensidadeFluxo(sc.nextInt());

                    ciclosMenstruais.add(cm);
                    break;

                case 2:

                    ciclosMenstruais.forEach(cm1 ->
                            System.out.println(
                                    "ID: " + cm1.getIdCicloMenstrual() +
                                            " | Início: " + cm1.getDataInicio() +
                                            " | Fim: " + cm1.getDataFinal() +
                                            " | Fluxo: " + cm1.getIntensidadeFluxo()
                            ));
                    break;

                case 3:

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    for (CicloMenstrual cm1 : ciclosMenstruais) {

                        if (cm1.getIdCicloMenstrual() == id) {

                            System.out.print("Novo fluxo: ");
                            cm1.setIntensidadeFluxo(sc.nextInt());
                        }
                    }

                    break;

                case 4:

                    System.out.print("ID: ");
                    int del = sc.nextInt();

                    ciclosMenstruais.removeIf(cm1 -> cm1.getIdCicloMenstrual() == del);
                    System.out.print("Excluído! ");
                    break;
            }

        } while (op != 0);
    }
}