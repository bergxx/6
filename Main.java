import java.util.*;

class Laptop {
    private String модель;
    private int ОЗУ;
    private int ОбъемЖД;
    private String операционнаяСистема;
    private String цвет;

    public Laptop(String модель, int ОЗУ, int ОбъемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.ОЗУ = ОЗУ;
        this.ОбъемЖД = ОбъемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    public String получитьМодель() {
        return модель;
    }

    public int получитьОЗУ() {
        return ОЗУ;
    }

    public int получитьОбъемЖД() {
        return ОбъемЖД;
    }

    public String получитьОперационнуюСистему() {
        return операционнаяСистема;
    }

    public String получитьЦвет() {
        return цвет;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "модель='" + модель + '\'' +
                ", ОЗУ=" + ОЗУ +
                ", ОбъемЖД=" + ОбъемЖД +
                ", операционнаяСистема='" + операционнаяСистема + '\'' +
                ", цвет='" + цвет + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> лэптопы = создатьМножество();
        Map<String, Object> критерии = запроситьКритерии();
        отфильтроватьИВывести(лэптопы, критерии);
    }

    public static Set<Laptop> создатьМножество() {
        Set<Laptop> лэптопы = new HashSet<>();
        лэптопы.add(new Laptop("Acer Swift", 16, 512, "Windows 10", "Серый"));
        лэптопы.add(new Laptop("MSI Prestige", 16, 256, "Windows 11", "Черный"));
        лэптопы.add(new Laptop("Huawei MateBook", 8, 256, "Windows 10", "Серебристый"));
        лэптопы.add(new Laptop("Samsung Galaxy Book", 12, 512, "Windows 10", "Синий"));
        лэптопы.add(new Laptop("LG Gram", 8, 256, "Windows 10", "Белый"));
        return лэптопы;
    }

    public static Map<String, Object> запроситьКритерии() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> критерии = new HashMap<>();
        System.out.println("Выберите критерии фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int выбор = scanner.nextInt();
        switch (выбор) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int ОЗУ = scanner.nextInt();
                критерии.put("ОЗУ", ОЗУ);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int ОбъемЖД = scanner.nextInt();
                критерии.put("ОбъемЖД", ОбъемЖД);
                break;
            case 3:
                scanner.nextLine(); // Очищаем буфер после ввода числа
                System.out.println("Введите операционную систему:");
                String операционнаяСистема = scanner.nextLine();
                критерии.put("ОперационнаяСистема", операционнаяСистема);
                break;
            case 4:
                scanner.nextLine(); // Очищаем буфер после ввода числа
                System.out.println("Введите цвет:");
                String цвет = scanner.nextLine().toLowerCase(); // Приводим введенный цвет к нижнему регистру
                критерии.put("Цвет", цвет);
                break;
            default:
                System.out.println("Некорректный выбор.");
        }
        return критерии;
    }

    public static void отфильтроватьИВывести(Set<Laptop> лэптопы, Map<String, Object> критерии) {
        System.out.println("Найденные лэптопы:");
        for (Laptop лэптоп : лэптопы) {
            boolean подходит = true;
            for (Map.Entry<String, Object> entry : критерии.entrySet()) {
                switch (entry.getKey()) {
                    case "ОЗУ":
                        if (лэптоп.получитьОЗУ() < (int) entry.getValue()) {
                            подходит = false;
                        }
                        break;
                    case "ОбъемЖД":
                        if (лэптоп.получитьОбъемЖД() < (int) entry.getValue()) {
                            подходит = false;
                        }
                        break;
                    case "ОперационнаяСистема":
                        if (!лэптоп.получитьОперационнуюСистему().equalsIgnoreCase((String) entry.getValue())) {
                            подходит = false;
                        }
                        break;
                    case "Цвет":
                        if (!лэптоп.получитьЦвет().equalsIgnoreCase((String) entry.getValue())) {
                            подходит = false;
                        }
                        break;
                    default:
                        System.out.println("Некорректный критерий.");
                }
            }
            if (подходит) {
                System.out.println(лэптоп);
            }
        }
    }
}