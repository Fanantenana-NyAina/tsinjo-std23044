package com.example.demo.dao;

import java.util.List;

public interface CRUD<E> {
  List<E> findAll(int page, int size);
}
