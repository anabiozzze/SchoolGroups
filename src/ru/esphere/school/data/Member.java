package ru.esphere.school.data;

import java.util.Objects;

public class Member {
    private final String name;
    private final Integer age;

    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return name.equals(member.name) &&
                age.equals(member.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

