package logic.unit;

import java.util.ArrayList;

public class BaseUnit {
	private int row;
	private int column;
	private boolean isWhite;
	private String name;

	protected int hp;
	protected int power;
	protected boolean isFlying;

	public BaseUnit(int startColumn, int startRow, boolean isWhite, String name) {
		this.power = 1;
		this.isFlying = false;
		this.setHp(2);

		this.setRow(startRow);
		this.setColumn(startColumn);

		this.setWhite(isWhite);
		this.setName(name);

	}

	public boolean move(int direction) {
		switch (direction) {
		case 0:
			if (!this.isOutOfRange(this.getRow() + 1)) {
				this.setRow(this.getRow() + 1);
				return true;
			}
			return false;
		case 1:
			if (!this.isOutOfRange(this.getColumn() + 1)) {
				this.setColumn(this.getColumn() + 1);
				return true;
			}
			return false;
		case 2:
			if (!this.isOutOfRange(this.getRow() - 1)) {
				this.setRow(this.getRow() - 1);
				return true;
			}
			return false;
		case 3:
			if (!this.isOutOfRange(this.getColumn() - 1)) {
				this.setColumn(this.getColumn() - 1);
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	public void attack(ArrayList<BaseUnit> targetPieces) {
		for (BaseUnit baseUnit : targetPieces) {
			if (!baseUnit.isFlying() && this.isSameTile(baseUnit)) {
				System.out.println(this.getName() + " attacks " + baseUnit.getName());
				baseUnit.setHp(baseUnit.getHp() - this.getPower());
			}
		}
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = (row < 0) ? 0 : (row > 4) ? 4 : row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = (column < 0) ? 0 : (column > 4) ? 4 : column;
	}

	public boolean isWhite() {
		return this.isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPower() {
		return this.power;
	}

	public boolean isFlying() {
		return this.isFlying;
	}

	protected boolean isOutOfRange(int lateralPosition) {
		return lateralPosition < 0 || lateralPosition > 4;
	}
	
	protected boolean isSameTile(BaseUnit baseUnit) {
		return baseUnit.getRow() == this.getRow() && baseUnit.getColumn() == this.getColumn();
	}
	
	protected boolean isSameTile(int row, int column) {
		return row == this.getRow() && column == this.getColumn();
	}
}
