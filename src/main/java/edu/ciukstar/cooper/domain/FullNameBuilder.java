package edu.ciukstar.cooper.domain;

/**
 *
 * @author sergiu
 */
public class FullNameBuilder {
    public static Surname from() {
        return new Steps();
    }
    
    public static interface Surname {
        Name surname(String value);
    }
    
    public static interface Name {
        Patronymic name(String value);
    }
    
    public static interface Patronymic {
        BuildStep patronymic(String value);
        BuildStep noPatronymic();
    }
    
    public static interface BuildStep {
        FullName get();
    }
    
    private static class Steps implements Surname, Name, Patronymic, BuildStep {

        private String surname;
        private String name;
        private String patronymic;

        @Override
        public Name surname(String value) {
            this.surname = value;
            return this;
        }

        @Override
        public Patronymic name(String value) {
            this.name = value;
            return this;
        }

        @Override
        public BuildStep patronymic(String value) {
            this.patronymic = value;
            return this;
        }

        @Override
        public BuildStep noPatronymic() {
            this.patronymic = null;
            return this;
        }

        @Override
        public FullName get() {
            return FullName.from(surname, name, patronymic);
        }
        
    }
}
