package ru.esphere.school.service;

import ru.esphere.school.data.MembersGroup;

import java.util.List;
import java.util.stream.Collectors;

public class Finder {
    /**
     * Поиск групп людей старше определенного возраста. *
     * @param groups группы
     * @param targetAge возраст для поиска
     * @return список имен групп из списка групп старше возраста targetAge
     **/

     public List<String> findOldMembers(List<MembersGroup> groups, int targetAge) throws IllegalArgumentException {
         // заменил Set на List, на случай совпадающих по имени участников
         List<String> groupsNames;

         // проверяем значения параметров на корректность
         if (targetAge<=0 || targetAge>100) throw new IllegalArgumentException("The member's age is can't be less than 0 or more then 100.");
         if (groups.isEmpty()) throw new IllegalArgumentException("List<MembersGroup> in findOldMembers() can't be empty.");


         /**
          * 1. запускаем поток списков клиентов;
          * 2. по каждому списку запускаем свой поток с фильтром по возрасту;
          * 3. сводим результаты обратно в один поток через flatMap();
          * 4. запускаем поток по каждому отфильтрованному Member для записи в коллекцию.
          */

         groupsNames = groups.stream().map(s -> s.getMembers())
                 .flatMap(x -> x.stream().filter(y -> y.getAge() > targetAge))
                 .map(z -> z.getName())
                 .collect(Collectors.toList());

         if (groupsNames.isEmpty()) {
             System.out.println("No matching age found.");
         }

         return groupsNames;
     }
}
