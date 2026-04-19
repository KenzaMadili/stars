package com.example.starsgallery.service;

import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;

import java.util.ArrayList;
import java.util.List;


public class StarService implements IDao<Star> {

    private List<Star> stars;
    private static StarService instance;

    // 🔒 Constructeur privé (Singleton)
    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    // 🔥 Singleton
    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    // 🔹 Données initiales (7 stars marocaines — cinéma, musique, sport)
    private void seed() {
        stars.add(new Star("Achraf Hakimi",       R.drawable.photo1, 4.9f)); // ⚽ Football
        stars.add(new Star("Saad Lamjarred",      R.drawable.photo2, 4.3f)); // 🎵 Musique
        stars.add(new Star("Leila Hadioui",       R.drawable.photo3, 4.5f)); // 🎬 Cinéma
        stars.add(new Star("Hakim Ziyech",        R.drawable.photo4, 4.6f)); // ⚽ Football
        stars.add(new Star("Douzi",               R.drawable.photo5, 4.4f)); // 🎵 Musique
        stars.add(new Star("Lubna Azabal",        R.drawable.photo6, 4.7f)); // 🎬 Cinéma
        stars.add(new Star("Yassine Bounou",      R.drawable.photo7, 4.8f)); // ⚽ Football
    }

    // 🔹 CREATE
    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    // 🔹 UPDATE
    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setRating(o.getRating());
                return true;
            }
        }
        return false;
    }

    // 🔹 DELETE
    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    // 🔹 FIND BY ID
    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // 🔹 FIND ALL
    @Override
    public List<Star> findAll() {
        return new ArrayList<>(stars);
    }
}