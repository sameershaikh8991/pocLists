package BuilderPattern;

public class User {

    private String name;
    private String email;

    public User(userBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + "]";
    }

    public static class userBuilder {

        private String name;
        private String email;

        public userBuilder(String name) {
            this.name = name;
        }

        public userBuilder name(String name) {
            this.name = name;
            return this;
        }

        public userBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User builduser() {
            return new User(this);
        }

    }

}