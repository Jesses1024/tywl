package com.puxintech.tywl.model.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanhai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private Integer id;

	private String name;

	private String type;

	private String description;
}
