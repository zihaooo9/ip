public enum Instruction {
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    HELP("help"),
    BYE("bye"),
    DELETE("delete"),
    FILTER("filter"),
    INVALID("invalid");

    public final String label;

    private Instruction(String label) {
        this.label = label;
    }

    /**
     * Look up an enum value by our label field.
     *
     * @param label command that user input.
     * @return a Command that corresponds to user input. Returns INVALID if input does not match any Commands.
     */
    public static Instruction valueOfLabel(String label) {
        for (Instruction c : values()) {
            if (c.label.equalsIgnoreCase(label)) {
                return c;
            }
        }
        return INVALID;
    }
}
