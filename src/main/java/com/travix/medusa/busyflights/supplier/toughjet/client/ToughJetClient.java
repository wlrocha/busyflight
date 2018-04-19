package com.travix.medusa.busyflights.supplier.toughjet.client;

import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetRequest;
import com.travix.medusa.busyflights.supplier.toughjet.model.ToughJetResponse;

import java.util.List;


public interface ToughJetClient
{
	List<ToughJetResponse> search(ToughJetRequest request) throws Exception;
}
