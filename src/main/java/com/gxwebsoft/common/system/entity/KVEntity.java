package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户
 *
 * @author WebSoft
 * @since 2021-08-28 11:31:06
 */
@Data
@ApiModel(description = "实体")
public class KVEntity<K,V> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected K k;
    protected V v;
  public KVEntity() {
    super();
  }

  public KVEntity(K k, V v) {
    super();
    this.k = k;
    this.v = v;
  }

  public static <K, V> KVEntity<K, V> build(K k, V v) {
    return new KVEntity<>(k, v);
  }

  public K getK() {
    return k;
  }

  public void setK(K k) {
    this.k = k;
  }

  public V getV() {
    return v;
  }

  public void setV(V v) {
    this.v = v;
  }

}
