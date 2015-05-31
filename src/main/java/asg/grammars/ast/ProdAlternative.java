package asg.grammars.ast;

import java.util.List;

import asg.grammars.parser.GrammarsParserParser.GExprPartsContext;

import com.google.common.collect.Lists;

public class ProdAlternative extends Production {
	public final List<Production> alternatives;


	public ProdAlternative(List<GExprPartsContext>  alts) {
		this.alternatives = Lists.newArrayList();
		for (GExprPartsContext g : alts) {
			alternatives.add(g.result);
			g.result.setParent(this);
		}
	}


	@Override
	public void print(StringBuilder tr) {
		boolean first = true;
		
		tr.append("(");
		for (Production a : alternatives) {
			if (!first) {
				tr.append(") | (");
			}
			a.print(tr);;
			first = false;
		}
		tr.append(")");
		
	}


	@Override
	public ProdType getType() {
		ProdType result = new ProdType();
		for (Production p: alternatives) {
			result = result.alternative(p.getType());
		}
		return result;
	}


	
}
