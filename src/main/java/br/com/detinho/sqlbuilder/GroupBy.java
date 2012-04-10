package br.com.detinho.sqlbuilder;

public final class GroupBy implements Writable {

    private final String alias;

    public GroupBy(String alias) {
        this.alias = alias;
    }

    @Override
    public String write() {
        return alias;
    }

}
