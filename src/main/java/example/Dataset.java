package example;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.general.DatasetUtilities;

public class Dataset
{
    private static final double[][] data = new double[][] {
            {12.0, 29.0, 36.0, 64.0, 58.0, 79.0, 70.0, 92.0},
            {49.0, 72.0, 74.0, 68.0, 88.0, 54.0, 38.0, 23.0},
            {41.0, 33.0, 22.0, 34.0, 62.0, 32.0, 42.0, 34.0}
    };

    public static CategoryDataset createDataset1()
    {
        DefaultCategoryDataset dataset;
        // row keys...
        final String series1 = "Чай"     ;
        final String series2 = "Кофе"    ;
        final String series3 = "Коктейль";
        // column keys...
        final String category1 = "Январь" ;
        final String category2 = "Февраль";

        dataset = new DefaultCategoryDataset();

        dataset.addValue(3.1, series1, category1);

        return dataset;
    }

    public static CategoryDataset createDataset2() {
        return DatasetUtilities.createCategoryDataset("Series ",
                "Factor ", data);
    }

    public static CategoryDataset createDataset3()
    {
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        result.addValue(16.1, "Чай (Офис 1)"     , "Январь" );
        result.addValue(28.2, "Чай (Офис 1)"     , "Февраль");
        result.addValue(29.5, "Кофе (Офис 1)"    , "Январь" );
        result.addValue(27.4, "Кофе (Офис 1)"    , "Февраль");
        result.addValue(16.7, "Коктейль (Офис 1)", "Январь" );
        result.addValue(29.8, "Коктейль (Офис 1)", "Февраль");
        return result;
    }
}