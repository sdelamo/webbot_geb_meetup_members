trait TraitAsCSV {
    List<String> propertyNames() {
        this.metaClass.properties*.name.findAll { it != 'class' }
    }

    String csvHeaders() {
        propertyNames().join(delimiter())
    }

    String asCSV() {
        def arr = []
        for ( def propName in propertyNames() ) {
            def v = this."$propName"
            arr <<  (v ?: '')
        }
        arr.join(delimiter())
    }

    static String delimiter() {
        ';'
    }
}
