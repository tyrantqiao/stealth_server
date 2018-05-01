package com.tyrantqiao.entity;

import javax.persistence.*;

/**
 * author: TyrantQiao
 * date: 2018/4/18
 * github: github.com/tyrantqiao
 */
@Entity
@Table(name = "catalog")
public class Catalog {
	/**                          
	 * TODO 目录id要不要细分再考虑下
	 */
	@Id
	@Column(name = "catalog_id",unique = true,length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long catalogId;

	@Column(name = "catalog_description",unique = true,length = 10)
	private String catalogDescription;
}
