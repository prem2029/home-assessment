package com.home.assessment.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.assessment.sequence.DatabaseSequence;


@Component
@Transactional
@Repository("sequenceGeneratorDao")
public class SequenceGeneratorDao extends AbstractDAO<DatabaseSequence>  {

	protected SequenceGeneratorDao() {
		super(DatabaseSequence.class);
	}	

	
	public long generateSequence(String seqName) {
		DatabaseSequence sequence = fetchById(seqName);
		long nextSeq = 1;
		if (sequence != null) {
			nextSeq = sequence.getSeq() + 1;			
		} else {
			sequence = new DatabaseSequence();
		}
		sequence.setSeq(nextSeq);
		sequence.setId(seqName);
		save(sequence);
		return nextSeq;
	}
}
