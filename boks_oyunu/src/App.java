//Java Sınıflar ile boks maçını simüle eden programı yazıyoruz.
class Dovuscu {
    String ad;
    double hasar;
    double can;
    double ağirlik;
    double dodge;

    Dovuscu(String ad, int can, int agirlik, double dodge) {
        this.ad = ad;
        this.ağirlik = agirlik;
        this.can = can;
        this.hasar = Math.random() * 50;
        this.dodge = dodge;
    }

    double hit(Dovuscu foe) {
       

        System.out.println(this.ad + "=>" + foe.ad + " 'a " + this.hasar + " " + "Vurdu");
        if (karsilik()) {
            System.err.println(foe.ad + "gelen hasari savurdu");
            return foe.can;
        }
        if (foe.can - this.hasar < 0) {
            return 0;
        } else {
            return foe.can - this.hasar;
        }

    }

    boolean karsilik() {
        double random = Math.random() * 100;
        return random <= this.dodge;
    }

}

class Ring {
    Dovuscu d1;
    Dovuscu d2;
    int minAgirlik;
    int maxAgirlik;

    Ring(Dovuscu d1, Dovuscu d2, int minAgirlik, int maxAgirlik) {
        this.d1 = d1;
        this.d2 = d2;
        this.maxAgirlik = maxAgirlik;
        this.minAgirlik = minAgirlik;
    }

    void run() {
        Dovuscu ilk = ilkBaslayan();
        Dovuscu ikinci = ilk == d1 ? d2 : d1;
        if (sikletKontrol()) {
            while (true) {
                System.out.println("----------YENİ ROUND----------");

                ikinci.can = ilk.hit(ikinci);
                if (isWin()) {
                    break;
                }
                ilk.can = ikinci.hit(ilk);
                if (isWin()) {
                    break;
                }
                skorYazdir();

            }

        } else {
            System.out.println("Sikletleri ayni degil");
        }
    }

    Dovuscu ilkBaslayan() {
        double r = Math.random() * 10;
        return r <= 5 ? d1 : d2;
    }

    boolean sikletKontrol() {
        return (d1.ağirlik >= minAgirlik && d1.ağirlik <= maxAgirlik) &&
                (d2.ağirlik >= minAgirlik && d2.ağirlik <= maxAgirlik);
    }

    boolean isWin() {
        if (d1.can == 0) {
            System.out.println(d2.ad + " " + "KAZANDİ");
            return true;
        }
        if (d2.can == 0) {
            System.out.println(d1.ad + " " + "KAZANDİ");
            return true;
        }

        return false;
    }

    void skorYazdir() {
        System.out.println(d1.ad + "Kalan Can " + d1.can);
        System.out.println(d2.ad + "Kalan Can " + d2.can);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Dovuscu d1 = new Dovuscu("mcgregor", 80, 80, 40);
        Dovuscu d2 = new Dovuscu("Habib Nurmagomedov", 120, 70, 70);
        Ring r = new Ring(d1, d2, 70, 100);
        r.run();

    }
}
