package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResult {
    private List<Comment> filmReply;
    private List<Comment> gameReply;
    private List<Comment> bookReply;
}
