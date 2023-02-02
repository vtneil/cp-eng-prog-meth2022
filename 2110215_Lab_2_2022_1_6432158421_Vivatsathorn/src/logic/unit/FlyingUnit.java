package logic.unit;

public class FlyingUnit extends BaseUnit{
	public FlyingUnit(int startColumn, int startRow, boolean isWhite, String name) {
		super(startColumn, startRow, isWhite, name);
		this.setHp(2);
		this.isFlying = true;
	}
	
	public FlyingUnit(BaseUnit baseUnit) {
		this(baseUnit.getColumn(), baseUnit.getRow(), baseUnit.isWhite(), baseUnit.getName());
	}
	
	public boolean move(int direction) {
		switch (direction) {
		case 0:
			if (!this.isOutOfRange(this.getRow() + 2)) {
				this.setRow(this.getRow() + 2);
				return true;
			}
			return false;
		case 1:
			if (!this.isOutOfRange(this.getColumn() + 2)) {
				this.setColumn(this.getColumn() + 2);
				return true;
			}
			return false;
		case 2:
			if (!this.isOutOfRange(this.getRow() - 2)) {
				this.setRow(this.getRow() - 2);
				return true;
			}
			return false;
		case 3:
			if (!this.isOutOfRange(this.getColumn() - 2)) {
				this.setColumn(this.getColumn() - 2);
				return true;
			}
			return false;
		default:
			return false;
		}
	}
}
