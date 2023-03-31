package com.mperson.missingchild;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseRecyclerOptions<T> {
    public static class Builder<T> {
        public void setQuery(Query missing_info, Class<MissingDetails> missingDetailsClass) {
            FirebaseDatabase.getInstance().getReference().child("Missing_Info");
        }
    }
}
