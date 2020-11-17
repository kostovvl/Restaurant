package api.gateway.domain;

import java.util.List;

public class TableContainer {

    private List<TableEntityDto> tables;

    public TableContainer() {
    }

    public TableContainer(List<TableEntityDto> tables) {
        this.tables = tables;
    }

    public List<TableEntityDto> getTables() {
        return tables;
    }

    public void setTables(List<TableEntityDto> tables) {
        this.tables = tables;
    }
}
