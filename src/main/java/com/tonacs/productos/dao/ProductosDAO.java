package com.tonacs.productos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonacs.productos.entitys.Producto;

public interface  ProductosDAO extends JpaRepository<Producto, Long>{

}
