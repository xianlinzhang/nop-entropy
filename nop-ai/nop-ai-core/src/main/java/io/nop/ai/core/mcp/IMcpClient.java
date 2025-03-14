package io.nop.ai.core.mcp;

import io.nop.ai.core.api.messages.ToolResponse;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface IMcpClient {
    List<ToolSpecification> listTools();

    CallToolResult executeTool(ToolResponse toolRequest);

    CompletionStage<CallToolResult> executeToolAsync(ToolResponse toolRequest);
}
