package com.tom.annotate;

import java.lang.reflect.Field;

public class Test {
	public static void main(String[] args) {
		Box b1 = new Box(110, 10);

		try {
			Box.check(b1);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("錯誤的參數");

		} catch (ReflectiveOperationException e) {
			System.out.println("反射錯誤");
		}
	}

}

class Box {
	@Check(max = 99, min = 1)
	public int n;

	@Check(max = 1000, min = -1)
	public int z;

	public Box(int n1, int n2) {
		this.n = n1;
		this.z = n2;
	}

	static void check(Box box) throws IllegalArgumentException, ReflectiveOperationException {

		// 取得 Box類的public變數
		for (Field f : box.getClass().getFields()) {

			// 取得註解 Check
			Check ck = f.getAnnotation(Check.class);

			if (ck != null) {

				// 透過反射取得f在box物件中的值
				// 因為不能確定類型，只能返回根類
				Object val = f.get(box);

				// int 會被自動裝箱成 Integer，且此處不能用基本類型
				if (val instanceof Integer i) {

					if (i < ck.min() || i > ck.max()) {
						throw new IllegalArgumentException(
								"Invalid arg " + f.getName() + " with value " + i.toString());
					}
				}
			}
		}

	}

}
