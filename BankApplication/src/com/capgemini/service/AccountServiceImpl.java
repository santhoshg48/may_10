package com.capgemini.service;

import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	@Override
	public boolean depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException
	{
		if(accountNumber<0){
			throw new InvalidAccountNumberException();
		}
		int existingAmount;
		int finalBal;
		Account account = accountRepository.searchAccount(accountNumber);
		existingAmount = account.getAmount();
		finalBal=existingAmount+amount;
		account.setAmount(finalBal);
		if(null!=account){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean withdrawAmount(int accountNumber, int amount) throws InvalidAccountNumberException
	{
		if(accountNumber<0){
			throw new InvalidAccountNumberException();
		}
		int existingAmount;
		int finalBal;
		Account account = accountRepository.searchAccount(accountNumber);
		existingAmount = account.getAmount();
		finalBal=existingAmount-amount;
		account.setAmount(finalBal);
		if(null!=account){
			return true;
		}
		return false;
	}

}
