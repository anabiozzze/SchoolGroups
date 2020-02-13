package ru.esphere.school.service;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

import java.util.ArrayList;
import java.util.List;

public class FinderTest {
    public static void main(String[] args) {
        Finder finder = new Finder();
        List<Member> allMembers = new ArrayList<>();
        List<MembersGroup> groups = new ArrayList<>();

        // создаем тестовый пул участников
        for (int i = 0; i < 10; i++) {
            allMembers.add(new Member("Member" + i, 20 + (int)(Math.random()*10)));
        }

        System.out.println("Все пользователи:");
        // выводим всех участников в консоль для проверки
        allMembers.stream().map(x-> x.getName() + ", age: " + x.getAge()).forEach(System.out::println);

        // создаем два тестовых MembersGroup из общего пула
        MembersGroup membersGroup1 = new MembersGroup("groupOne", allMembers.subList(0,5));
        MembersGroup membersGroup2 = new MembersGroup("groupTwo",  allMembers.subList(5,10));
        groups.add(membersGroup1);
        groups.add(membersGroup2);


        // проверяем метод findOldMembers и выводим его результаты
        System.out.println("\nРезультаты поиска:");
        try {
            finder.findOldMembers(groups, 27).stream().forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // проверяем метод findOldMembers на некорректные аргументы
        System.out.println("\nПроверка на пустой лист:");
        try {
            finder.findOldMembers(new ArrayList<>(), 27).stream().forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println("\nПроверка на некорректный возраст:");
        try {
            finder.findOldMembers(new ArrayList<>(), 127).stream().forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
