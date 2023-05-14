package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
    private PageBean filmResults;
    private PageBean gameResults;
    private PageBean bookResults;
}
