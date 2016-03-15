trait TraitAsCSV {
    List<String> propertyNames() {
        this.metaClass.properties.collect { it.name }.findAll { it != 'class'}
    }

    String csvHeaders() {
        propertyNames().join(delimiter())
    }

    String asCSV() {
        def str = ""
        def arr = []
        for(def propName in propertyNames()) {
            def v = this."$propName"
            arr <<  (v ?: '')
        }
        arr.join(delimiter())
    }

    static String delimiter() {
        ';'
    }
}