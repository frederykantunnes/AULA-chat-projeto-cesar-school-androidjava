package br.com.frederykantunnes.projetochatcesar_androidavancado.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    public FirebaseUser usuario (){
        return FirebaseAuth.getInstance().getCurrentUser();
    }
}
