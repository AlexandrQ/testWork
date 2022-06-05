package com.testWork.dao;

import com.testWork.models.Movie;
import com.testWork.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieMapper {

    public void deleteMovie(Long id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("deleteMovie", id);
        session.commit();
        session.close();
    }

    public void insertMovie(Movie movie) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("insertMovie", movie);
        session.commit();
        session.close();
    }

    public void updateMovie(Movie movie) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("updateMovie", movie);
        session.commit();
        session.close();
    }

    public List<Movie> getAllMovies() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        List<Movie> result = session.selectList("getAllMovies");
        session.commit();
        session.close();
        return result;
    }

    public Movie getMovieById(Long id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Movie movie = session.selectOne("getMovieById", id);
        session.commit();
        session.close();
        return movie;
    }

}
