package br.com.mercadolivre.socialmeli.user.enums;

public enum OrderBy {
    
    NAME_ASC("name_asc"),
    NAME_DESC("name_desc"),
    DATE_ASC("date_asc"),
    DATE_DESC("date_desc");

    private String id;    

    private OrderBy(String id) {
        this.id = id;
    }

    public String getValue() {
        return id;
    }

}
