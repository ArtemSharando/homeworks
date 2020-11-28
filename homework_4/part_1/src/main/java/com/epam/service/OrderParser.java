package com.epam.service;

import com.epam.entity.Order;
import com.epam.entity.OrderState;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderParser {

    public List<Order> parse(String csvFile) throws IOException {
        List<Order> orders = new ArrayList<>();
        FileReader filereader = new FileReader(csvFile);

        // create csvParser object with
        // custom seperator semi-colon
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        // create csvReader object with
        // parameter filereader and parser
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        // Read all data at once
        List<String[]> allData = csvReader.readAll();

        for (String[] row : allData) {
            Order order = new Order();
            int counter = 0;
            for (String cell : row) {
                counter++;
                if (counter == 1) {
                    order.setId(Long.parseLong(cell));
                } else {
                    order.setOrderState(OrderState.valueOf(cell));
                }
            }
            orders.add(order);
        }
        return orders;
    }
}
