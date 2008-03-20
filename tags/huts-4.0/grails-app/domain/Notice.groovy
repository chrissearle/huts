class Notice {
    String title
    String text
    Boolean shown = true;

    static constraints = {
        title()
        text()
        shown()
    }

    String toString() { "${title}" }
}
