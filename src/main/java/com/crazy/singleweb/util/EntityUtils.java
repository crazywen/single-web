/*   
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.crazy.singleweb.util;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

import com.crazy.singleweb.entity.BaseEntity;

public class EntityUtils {
	public static final List<BaseEntity> EMPTY_ENTITYS = new EmptyList();

	/**
	 * @serial include
	 */
	private static class EmptyList extends AbstractList<BaseEntity> implements
			RandomAccess, Serializable {
		private static final long serialVersionUID = 1008253288771183091L;

		public int size() {
			return 0;
		}

		public boolean contains(Object obj) {
			return false;
		}

		public BaseEntity get(int index) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}

		// Preserves singleton property
		private Object readResolve() {
			return EMPTY_ENTITYS;
		}
	}
}
