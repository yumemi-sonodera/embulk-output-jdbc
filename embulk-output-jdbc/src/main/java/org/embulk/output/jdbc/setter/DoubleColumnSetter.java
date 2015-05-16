package org.embulk.output.jdbc.setter;

import java.io.IOException;
import java.sql.SQLException;
import org.embulk.spi.ColumnVisitor;
import org.embulk.spi.time.Timestamp;
import org.embulk.output.jdbc.JdbcColumn;
import org.embulk.output.jdbc.BatchInsert;

public class DoubleColumnSetter
        extends ColumnSetter
{
    public DoubleColumnSetter(BatchInsert batch, JdbcColumn column)
    {
        super(batch, column);
    }

    @Override
    public void booleanValue(boolean v) throws IOException, SQLException
    {
        batch.setDouble(v ? 1.0 : 0.0);
    }

    @Override
    public void longValue(long v) throws IOException, SQLException
    {
        batch.setDouble((double) v);
    }

    @Override
    public void doubleValue(double v) throws IOException, SQLException
    {
        batch.setDouble(v);
    }

    @Override
    public void stringValue(String v) throws IOException, SQLException
    {
        double dv;
        try {
            dv = Double.parseDouble(v);
        } catch (NumberFormatException e) {
            nullValue();
            return;
        }
        batch.setDouble(dv);
    }

    @Override
    public void timestampValue(Timestamp v) throws IOException, SQLException
    {
        nullValue();
    }
}
