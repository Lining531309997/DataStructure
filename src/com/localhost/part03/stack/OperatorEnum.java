package com.localhost.part03.stack;

public enum OperatorEnum {

	PLUS('+',1), MINUS('-',1), MULTI('*',2), DIVIDE('/',2), OPENPAREN('(',0);
	
	private Character operator;
	
	private int priority;

	private OperatorEnum(Character operator, int priority) {
		this.operator = operator;
		this.priority = priority;
	}
	
	/**
	 * 获取运算符的对应优先级
	 * @param operator 运算符
	 * @return 对应的优先级
	 */
	public static int priorityOf(char operator) {
		for (OperatorEnum oper : values()) {
			if (oper.getOperator() == operator) {
				return oper.getPriority();
			}
		}
		return operator;
	}
	
	/**
	 * @return the operator
	 */
	public Character getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Character operator) {
		this.operator = operator;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
