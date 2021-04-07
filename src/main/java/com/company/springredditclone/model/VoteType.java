package com.company.springredditclone.model;

public enum VoteType {
    UPVOTE_TYPE(1), DOWNVOTE_TYPE(-1),
    ;

    VoteType(int direction) {
    }
}
