package com.example.starsgallery.dao;

import java.util.List;


public interface IDao<T> {

    // 🔹 Ajouter un élément
    boolean create(T o);

    // 🔹 Mettre à jour un élément
    boolean update(T o);

    // 🔹 Supprimer un élément
    boolean delete(T o);

    // 🔹 Trouver un élément par son ID
    T findById(int id);

    // 🔹 Récupérer tous les éléments
    List<T> findAll();
}