package at.htlgkr.print;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.compare;

public class Main{
    public static void main(String[] args) throws IOException {

        List<Weapon> list = new ArrayList<>();

        //Liste befüllen mit at.htlgkr.print.Weapon Objekte
        list = Files.lines(new File("weapons.csv").toPath())
                .skip(1)
                .map(item -> item.split(";"))
                .map(item -> new Weapon(
                        item[0],
                        CombatType.valueOf(item[1]),
                        DamageType.valueOf(item[2]),
                        Integer.parseInt(item[3]),
                        Integer.parseInt(item[4]),
                        Integer.parseInt(item[5]),
                        Integer.parseInt(item[6])
                ))
                .collect(Collectors.toList());

        //Liste wird nach Damage sortiert
        list.sort((x1, x2) -> Integer.compare(x1.getDamage(), x2.getDamage()));

        //Liste wird nach Alphabet von at.htlgkr.print.CombatType dann at.htlgkr.print.DamageType dann Name sortiert
        list.sort(Comparator.comparing(Weapon::getCombatType)
                .thenComparing(Weapon::getDamageType)
                .thenComparing(Weapon::getName)
        );


        //Aufabe 1.5
        Printable printable = list1 -> list1.forEach(System.out::println);
        printable.print(list);
    }
}