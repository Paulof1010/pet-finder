/**
 * Report Service
 * Handles API communication for reports
 */

const API_BASE = "http://13.40.192.86:8080";

async function submitReport(reportData) {
  const response = await fetch(`${API_BASE}/occurrence`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(reportData),
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Failed to submit report: ${errorText}`);
  }

  // ✅ Handle empty response body safely
  const text = await response.text();
  return text ? JSON.parse(text) : null;
}

export const reportService = {
  submitReport,
};
