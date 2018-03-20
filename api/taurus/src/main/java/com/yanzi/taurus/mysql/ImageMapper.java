package com.yanzi.taurus.mysql;

import org.apache.ibatis.annotations.Param;

import com.yanzi.taurus.entity.ImageInfo; 
public interface ImageMapper {
    public void add(@Param(value = "image")ImageInfo imageInfo);
}
