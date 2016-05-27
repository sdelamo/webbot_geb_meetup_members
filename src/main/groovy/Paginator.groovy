interface Paginator {

    void toPage(int pageNumber)

    void nextPage()

    void previousPage()

    boolean isFirstPage()

    boolean isLastPage()
}
