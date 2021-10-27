package com.litter.litter.service.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.litter.litter.model.MessageFirebase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreServiceImpl implements FirestoreService {

    private Firestore db = FirestoreClient.getFirestore();

    @Override
    public String save(MessageFirebase messageFirebase) throws ExecutionException, InterruptedException {
        String documentName = LocalDateTime.now().toString();
        DocumentReference documentReference = db.collection("messages").document(documentName);
        Map<String, Object> data = new HashMap<>();
        data.put("content", messageFirebase.getContent());
        data.put("userID", messageFirebase.getUserID());
        data.put("groupID", messageFirebase.getGroupID());
        data.put("datetime", messageFirebase.getDatetime());

        documentReference.set(data);

        ApiFuture<QuerySnapshot> query = db.collection("messages").get();
        QuerySnapshot querySnapshots = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = querySnapshots.getDocuments();
        String queryData = "";
        for (QueryDocumentSnapshot snapshot : documentSnapshots) {
            if (snapshot.get("datetime").equals(messageFirebase.getDatetime())) {
                queryData = snapshot.getData().toString();
            }
        }
        return queryData;
    }
}
