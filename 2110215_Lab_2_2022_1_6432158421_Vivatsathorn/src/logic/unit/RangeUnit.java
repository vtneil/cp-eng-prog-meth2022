package logic.unit;

import java.util.ArrayList;

public class RangeUnit extends BaseUnit {
	public RangeUnit(int startColumn, int startRow, boolean isWhite, String name) {
		super(startColumn, startRow, isWhite, name);
		this.setHp(2);
	}
	
	public RangeUnit(BaseUnit baseUnit) {
		this(baseUnit.getColumn(), baseUnit.getRow(), baseUnit.isWhite(), baseUnit.getName());
	}

	public void attack(ArrayList<BaseUnit> targetPieces) {
		int rowToAttack = this.getRow();
		int columnToAttack = this.getColumn();
		if (this.isWhite()) {
			rowToAttack++;
		} else {
			rowToAttack--;
		}
		for (BaseUnit baseUnit : targetPieces) {
			if (baseUnit.isSameTile(rowToAttack, columnToAttack)) {
				System.out.println(this.getName() + " attacks " + baseUnit.getName());
				baseUnit.setHp(baseUnit.getHp() - this.getPower());
			}
		}
	}

}
