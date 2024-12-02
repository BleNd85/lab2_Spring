package org.example.lab2.Service.Sort;

import java.util.List;

public interface SortItemsByCategory<T> {
    List<T> sortByCategory(List<T> items);
}
