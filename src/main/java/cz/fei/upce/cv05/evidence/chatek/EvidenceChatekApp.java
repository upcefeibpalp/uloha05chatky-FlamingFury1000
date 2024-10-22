package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    static Scanner scanner = new Scanner(System.in);

    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 5;
    static final int MAX_VELIKOST_CHATKY = 10;

    static int[] chatky = new int[VELIKOST_KEMPU];

    public static void main(String[] args) {
        // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)

        // Definovani pole podle velikosti kempu (poctu chatek)
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);
            
            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();
            
            
            // Switch na vyber moznosti
            switch (operace) {
                case VYPIS_CHATEK ->
                    vypisChatek();
                case VYPIS_KONKRETNI_CHATKU ->
                    vypisKonkretniChatku();
                case PRIDANI_NAVSTEVNIKU ->
                    pridaniNavstevniku();
                case ODEBRANI_NAVSTEVNIKU ->
                    odebraniNavstevniku();
                case CELKOVA_OBSAZENOST ->
                    celkovaObsazenost();
                case VYPIS_PRAZDNE_CHATKY ->
                    vypisPrazdneChatky();
                case KONEC_PROGRAMU ->
                    System.out.println("Konec programu");
                default ->
                    System.err.println("Neplatna volba");
            }
        } while (operace != 0);
    }

    private static void vypisPrazdneChatky() {
        // Projdi cele pole od <0, VELIKOST) a vypis pouze, pokud hodnota je 0
        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0) {
                System.out.println("Chatka [" + (i + 1) + "]");
            }
        }
    }

    private static void celkovaObsazenost() {
        // Inicializace promenne souctu
        int pocetObyvatel = 0;
        // Pripocti vsechny hodnoty v poli do pormenne pocetObyvatel
        for (int i = 0; i < chatky.length; i++) {
            pocetObyvatel = chatky[i] + pocetObyvatel;
        }
        System.out.println("Celkova obsazenost cini: " + pocetObyvatel);
    }

    private static void odebraniNavstevniku() {
        // Ziskani cisla chatky od uzivatele
        System.out.print("Zadej cislo chatky: ");
        // Odecteni 1 protoze uzivatel cisluje chatky o 1, ale program od 0
        int cisloChatky = scanner.nextInt() - 1;
        // Zaporne nebo cislo vetsi nez je pocet chatek je nevalidni vstup
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }
        // Ziskani poctu navstevniku, kteri maji byt smazani
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();
        // Zaporne cislo nebo prilis velky nevalidni vstup
        if (pocetNavstevniku <= 0 || pocetNavstevniku > chatky[cisloChatky]) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
        } else {
            // Odebrej pocet navstevniku od stare hodnoty
            chatky[cisloChatky] = chatky[cisloChatky] - pocetNavstevniku;
        }
    }

    private static void pridaniNavstevniku() {
        // Ziskani cisla chatky od uzivatele
        System.out.print("Zadej cislo chatky: ");
        // Odecteni 1 protoze uzivatel cisluje chatky o 1, ale program od 0
        int cisloChatky = scanner.nextInt() - 1;
        // Zaporne nebo cislo vetsi nez je pocet chatek je nevalidni vstup
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }
        // Ziskani poctu navstevniku, kteri se chteji v chatce ubytovat
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();
        // Zaporne cislo nebo prilis velky nevalidni vstup
        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }
        // Pokud je pocet uz ubytovanych plus ty co se chteji ubytovat vetsi 
        // nez kapacita chatky je to nevalidni vstup
        if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return;
        }
        // Pridej nove ubytovane do chatky k tem co uz tam jsou
        chatky[cisloChatky] = pocetNavstevniku + chatky[cisloChatky];
    }

    private static void vypisKonkretniChatku() {
        // Ziskani cisla chatky od uzivatele
        System.out.print("Zadej cislo chatky: ");
        // Odecteni 1 protoze uzivatel cisluje chatky o 1, ale program od 0
        int cisloChatky = scanner.nextInt() - 1;

        // Zaporne nebo cislo vetsi nez je pocet chatek je nevalidni vstup
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
        } else {
            System.out.println("Chatka [" + (cisloChatky + 1) + "] = "
                    + chatky[cisloChatky]);
        }
    }

    private static void vypisChatek() {
        // Projdi cele pole od <0, VELIKOST) a vypis kazdy index
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }
}
